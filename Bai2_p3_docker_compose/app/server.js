const express = require('express');
const { MongoClient } = require('mongodb');

const app = express();
const port = 3000;

// Connection URL cho MongoDB
const url = 'mongodb://mongodb:27017';
const dbName = 'mydb';

app.use(express.json());

// API endpoint ví dụ
app.get('/health', (req, res) => {
  res.json({ status: 'OK' });
});

app.post('/data', async (req, res) => {
  try {
    const client = await MongoClient.connect(url);
    const db = client.db(dbName);
    const result = await db.collection('items').insertOne(req.body);
    res.json(result);
    client.close();
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});