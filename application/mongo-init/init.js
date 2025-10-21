// create db
candidateDb = db.getSiblingDB('candidate');

// create user
candidateDb.createUser({
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
candidateDb.createCollection('candidate');

// create docs
candidateDb.candidate.insertMany([
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



// create db
jobDb = db.getSiblingDB('job');

// create user
jobDb.createUser({
    user: "usr1",
    pwd: "pwd1",
    roles:[
        {
            role: "readWrite",
            db: "job"
        }
    ]
});

// create collection
jobDb.createCollection('job');

// create docs
jobDb.job.insertMany([
    {
        description: "senior java dev",
        company: "amazon",
        skills: [ "java", "spring", "docker" ],
        salary: 100000,
        isRemote: false
    },
    {
        description: "junior java dev",
        company: "apple",
        skills: [ "java" ],
        salary: 50000,
        isRemote: false
    },
    {
        description: "backend engineer",
        company: "google",
        skills: [ "java", "spring", "hibernate" ],
        salary: 80000,
        isRemote: true
    },
    {
        description: "frontend engineer",
        company: "meta",
        skills: [ "angular", "html", "css" ],
        salary: 60000,
        isRemote: true
    }
]);

