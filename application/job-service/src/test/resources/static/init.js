

// create db
db = db.getSiblingDB('job');

// create user
db.createUser({
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
db.createCollection('job');

// create docs
db.job.insertMany([
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