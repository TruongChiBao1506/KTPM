import requests

def test_hello_endpoint():
    response = requests.get("http://app:5000/")
    assert response.status_code == 200
    assert "Hello from the Voting App!" in response.text