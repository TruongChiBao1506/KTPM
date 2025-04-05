const express = require('express');
const mysql = require('mysql2/promise');

const app = express();
const port = 3000;

const dbConfig = {
  host: 'mysql',
  user: 'user',
  password: 'password',
  database: 'mydb'
};

async function initializeDatabase() {
  let attempts = 0;
  const maxAttempts = 10;
  while (attempts < maxAttempts) {
    try {
      const connection = await mysql.createConnection(dbConfig);
      await connection.execute(`
        CREATE TABLE IF NOT EXISTS users (
          id INT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255) NOT NULL
        )
      `);
      await connection.execute(`
        INSERT INTO users (name) VALUES ('Alice'), ('Bob')
        ON DUPLICATE KEY UPDATE name=name
      `);
      await connection.end();
      console.log('Database initialized successfully');
      break;
    } catch (error) {
      attempts++;
      console.log(`Database connection failed, retrying (${attempts}/${maxAttempts})...`, error.message);
      if (attempts === maxAttempts) throw new Error('Failed to connect to MySQL after retries');
      await new Promise(resolve => setTimeout(resolve, 2000)); // Chờ 2 giây
    }
  }
}

app.get('/users', async (req, res) => {
  try {
    const connection = await mysql.createConnection(dbConfig);
    const [rows] = await connection.execute('SELECT * FROM users');
    await connection.end();
    res.json(rows);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

app.listen(port, async () => {
  console.log(`Server running at http://localhost:${port}`);
  await initializeDatabase();
});