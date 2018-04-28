const AWS = require('aws-sdk');

exports.handler = (event, context, callback) => {
    // TODO implement
    AWS.config.update({
        region: 'us-west-2'
    });
    
    const docClient = new AWS.DynamoDB.DocumentClient();
    
    const table = 'Student';
    
    const paramsForScan = {
        TableName: 'Student'
    };
    
    console.log('Scanning Student table.');
    
    docClient.scan(paramsForScan, onScan);
    
    function onScan(err, data) {
        if (err) {
            console.error('Unable to scan the table. Error JSON:', JSON.stringify(err, null, 2));
        } else {
            console.log('Scan succeeded');
            let studentList = [];
            data.Items.forEach(function(student){
                studentList.push(student.Name);
            });
            
            if (studentList.includes(event.name)) {
                callback(null, event);
            } else {
                const params = {
                    TableName: table,
                    Item: {
                        'Name': event.name,
                        'Item': event.email
                    }
                };
                
                console.log('Adding a new item...');
                docClient.put(params, function(err, data){
                    if (err) {
                        console.error('Unable to add item. Error JSON:', JSON.stringify(err, null, 2));
                    } else {
                        console.log('Added item:', JSON.stringify(data, null, 2));
                    }
                });
                
                if (typeof data.LastEvaluatedKey != 'undefined') {
                    console.log('Scanning for more...');
                    params.ExclusiveStartKey = data.LastEvaluatedKey;
                    docClient.scan(params, onScan);
                } else {
                    callback(null, event);
                }
            }
        }
    }
};
