const AWS = require('aws-sdk');

exports.handler = (event, context, callback) => {
    
    AWS.config.update({
        region: 'us-west-2'
    });
    
    const docClient = new AWS.DynamoDB.DocumentClient();
    
    const courses = [];
    event.course.forEach(function(course) {
        courses.push('Course' + course);
    })
    
    let registeredCourse = [];
    let unregisteredCourse = [];
    
    event.isRegister = 'true';
    
    courses.forEach(function(course){
        const params = {
            TableName: course,
            KeyConditionExpression: '#n = :name',
            ExpressionAttributeNames: {
                '#n': 'StudentName'
            },
            ExpressionAttributeValues: {
                ':name': event.name
            }
        };
        
        docClient.query(params, function(err, data){
            if (err) {
                console.error('Unable to query. Error:', JSON.stringify(err, null, 2));
            } else {
                console.log('Query succeeded');
                if (data.Items.length === 0) {
                    unregisteredCourse.push(course);
                    event.unregisteredCourse = unregisteredCourse;
                    event.isRegister = 'false';
                    callback(null, event);
                } else {
                    registeredCourse.push(course);
                    event.registeredCourse = registeredCourse;
                    callback(null, event);
                }
            }
        });
    });
};