const AWS = require('aws-sdk');

exports.handler = (event, context, callback) => {
    
    AWS.config.update({
        region: 'us-west-2'
    });
    
    const docClient = new AWS.DynamoDB.DocumentClient();
    
    let paidCourse = [];
    
    event.registeredCourse.forEach(function(course){
        const table = course;
        
        const paramsScan = {
            TableName: table
        };
        
        console.log("Scanning Course table.");
        
        docClient.scan(paramsScan, onScan);
        
        function onScan(err, data) {
            if (err) {
                console.error('Unable to scan the table. Error JSON:', JSON.stringify(err, null, 2));
            } else {
                console.log("Scan succeeded.");
                data.Items.forEach(function(student) {
                   if (student.unPaid > 0) {
                        
                        const params = {
                            TableName: table,
                            Key: {
                                'StudentName': event.name
                            },
                            UpdateExpression: 'set unPaid = :m',
                            ExpressionAttributeValues: {
                                ':m': 0
                            },
                            ReturnValues: 'UPDATED_NEW'
                        };
                        
                        console.log('Updating the item...');
                        docClient.update(params, function(err, data) {
                            if (err) {
                                console.error('Unable to update item. Error JSON:', JSON.stringify((err, null, 2)));
                            } else {
                                console.log('UpdateItem succeeded:', JSON.stringify(data, null, 2));
                                paidCourse.push(course);
                                event.paidCourse = paidCourse;
                                callback(null, event);
                            }
                        });
                   } else {
                       event.paidCourse = paidCourse;
                       callback(null, event);
                   } 
                });
        
                if (typeof data.LastEvaluatedKey != 'undefined') {
                    console.log('Scanning for more...');
                    paramsScan.ExclusiveStartKey = data.LastEvaluatedKey;
                    docClient.scan(paramsScan, onScan);
                }
            }
        }
    });
};