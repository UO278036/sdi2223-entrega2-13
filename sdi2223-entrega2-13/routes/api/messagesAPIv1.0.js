const {ObjectId} = require("mongodb");
const {validationResult} = require('express-validator')
const {messageValidatorInsert} = require('./messagesValidator')
module.exports = function (app, offersRepository, messagesRepository) {
    app.post('/api/v1.0/offers/messages', messageValidatorInsert, function(req, res) {
        try {
            const errors = validationResult(req);
            if (!errors.isEmpty()) {
                res.status(422);
                res.json({errors: errors.array()})
            }
            else {
                let message = {
                    buyer: res.user, // req.session.user (?)
                    seller: req.body.seller,
                    offer: req.body.offer,
                    text: req.body.text,
                    date: Date.now(),
                    read: false
                }

                messagesRepository.insertMessage(message).then((messageId) => {
                    if (messageId === null) {
                        res.status(409);
                        res.json({error: "No se ha podido enviar el mensaje."});
                    } else {
                        res.status(201);
                        res.json({
                            message: "Mensaje enviado correctamente.",
                            _id: messageId
                        })
                    }
                });
            }
        } catch (e) {
            res.status(500);
            res.json({error: "Se ha producido un error al intentar crear el mensaje: " + e})
        }
    })

    app.get("/api/v1.0/offers/messages/:id", function (req, res) {
        let filter = {
            $or: [ {seller: res.user}, {buyer: res.user} ],
            offer: req.params.id
        };
        let options = {};
        messagesRepository.getMessages(filter, options).then(messages => {
            res.status(200);
            res.send({messages: messages})
        }).catch(error => {
            res.status(500);
            res.json({ error: "Se ha producido un error al recuperar los mensajes." })
        });
    });
}