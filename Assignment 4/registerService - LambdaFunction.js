const AWS = require('aws-sdk');

exports.handler = (event, context, callback) => {
    
    const docClient = new AWS.DynamoDB.DocumentClient();
    
    const cost = 1000;
    
    event.unregisteredCourse.forEach(function(course){
        const table = course;
        
        const params = {
            TableName: table,
            Item: {
                'StudentName': event.name,
                'Email': event.email,
                'unPayed': cost
            }
        };
        
        console.log('Adding a new item...');
        docClient.put(params, function(err, data) {
            if (err) {
                console.error('Unable to add item. Error JSON:', JSON.stringify(err, null, 2));
            } else {
                console.log("Added item:", JSON.stringify(data, null, 2));
            }
        });
    })
    
    callback(null, event);
};