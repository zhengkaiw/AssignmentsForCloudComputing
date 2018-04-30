const AWS = require('aws-sdk');
const sns = new AWS.SNS();

exports.handler = (event, context, callback) => {
    
    if (event.paidCourse.length === 0) {
        callback(null, event);
    } else {
        let courses = '';
    
        event.paidCourse.forEach(function(course){
            courses += (', ' + course);    
        });
        
        courses = courses.slice(8);
    
        const messageForStudent = {
            'default': 'Dear ' + event.name + '\n\nCourse ' + courses + ' Registered successfully',
            'APNS': '{\'aps\':{\'alert\': {\'title\': \'Hello\',\'body\': \'You have successfully registered ${courses}\'},\'badge\':1}}'
        }
        
        const params = {
            Message: JSON.stringify(messageForStudent),
            MessageStructure: 'json',
            TopicArn: '***:testForCourseRegister'
        };
        
        const publishPromise = sns.publish(params).promise();
        publishPromise
        .then((response) => {
            callback(null, 'Message published');
        }, (error) => {
            console.log('We had an error');
            callback('Message cant publish');
        });
    }
};