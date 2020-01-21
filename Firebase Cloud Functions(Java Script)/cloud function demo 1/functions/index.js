const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.helloWorldAgain = functions.https
    .onRequest((request, response) => {
    admin.database()
        .ref("functionData")
        .set("Hello world from function!");
    response.send("Hello from Firebase! SSUET");
});

exports.helloWorldGet = functions.https.onRequest((request, response) => {
    admin.database().ref("functionData").once("value", (snap) => {
        response.send(snap.val());
    });
});


exports.onFunValueChange = functions.database.ref("functionData").onUpdate((snapshot, context) => {
    let myVal = snapshot.after.val().toUpperCase()+" SSUET";
    admin.database().ref("hello_node").set(myVal);
    return snapshot.before.val()
});

exports.ondelete = functions.database.ref("Class").onDelete((snapshot,context) => {
    admin.database().ref("hello_node").remove().catch();
});