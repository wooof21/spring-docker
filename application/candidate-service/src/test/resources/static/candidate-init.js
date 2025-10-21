// create db
db = db.getSiblingDB('candidate');

// create user
db.createUser({
    user: 'usr1',
    pwd: 'pwd1',
    roles:[
        {
            role: 'readWrite',
            db: 'candidate'
        }
    ]
});

// create collection
db.createCollection('candidate');

// create docs
db.candidate.insertMany([
    {
        _id: '1',
        name: 'John',
        skills: [ 'java', 'spring' ]
    },
    {
        _id: '2',
        name: 'Jane',
        skills: [ 'docker' ]
    },
    {
        _id: '3',
        name: 'Jake',
        skills: [ 'angular' ]
    }
]);