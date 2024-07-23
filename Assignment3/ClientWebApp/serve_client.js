const express = require('express');
const cors = require('cors');
const path = require('path');
const app = express();
const port = 8080;

app.use(cors());
app.use(express.json());

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'client.html'));
});

app.listen(port, () => {
  console.log(`Shipping Client is running on http://localhost:${port}`);
});
