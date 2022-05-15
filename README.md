Look in http://localhost:5500/swagger-ui/index.html

port 5500
for terminal:
docker build -t cardtransfer:latest .
docker run -itd --name cardtransfer cardtransfer:latest
docker-compose up

1. POST /transfer
{
    "cardFromNumber": "1234123412341234",
    "cardFromValidTill": "08/20",
    "cardFromCVV": "123",
    "cardToNumber": "2345234523452345",
    "amount": {
        "value": 100,
        "currency": "RUR"
    }
}
// opens the transactional, and waits for confirm with SMS-code (allways deafault "0000")
// body must be valid

3. POST /confirmOperation
{
   "code":"0000",
   "operationId":"1"
}
//commits the transactional