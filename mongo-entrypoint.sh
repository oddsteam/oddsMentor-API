#!/bin/bash
mongo -u ${MONGO_INITDB_ROOT_USERNAME} -p ${MONGO_INITDB_ROOT_PASSWORD} <<EOF
use ${MONGO_INITDB_DATABASE}
db.createCollection("users")
db.users.insert({
  _id: new ObjectId("62c70dedf9c68f9f4d00cf40"),
  "firstNameEN": "Chandara",
  "lastNameEN": "Sin",
  "firstNameTH": "จันทร์ดารา",
  "lastNameTH": "ซิน",
  "nickname": "โดม",
  "email": "dome@odds.team",
  "accountType": "Cooperative Education 2022",
  "biography": "Hello, I'm Chandara Sin. I'm a software developer. I'm currently working at Odd-e (Thailand) in Mola Mola team.",
  "team": "Molamola",
  "position": "Software Developer",
  "profileImageUrl": "https://drive.google.com/file/d/18SM8TbikJQt-RH2LdS_MbzGGod-dXHgx/view?usp=sharing",
  "expertise": ["62c5a427b1192a6c1e8d1569", "62c5b2004743e83ab91f2a26", "62c65c85020d0f6ecbdc58a5"],
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.users.insert({
  _id: new ObjectId("62c70dedf9c68f9f4d00cf42"),
  "firstNameEN": "Watcharapol",
  "lastNameEN": "Sanitwong",
  "firstNameTH": "วัชรพล",
  "lastNameTH": "สนิทวงษ์",
  "nickname": "ตั้ม",
  "email": "tumit@odds.team",
  "accountType": "OddsMember",
  "biography": "Hello, I'm Watcharapol Sanitwong. I'm a software developer. I'm currently working at Odd-e (Thailand) in SET team.",
  "team": "SET",
  "position": "Software Developer",
  "profileImageUrl": "https://cdn.discordapp.com/attachments/994528164481085442/994528284849225758/Screen_Shot_2565-07-07_at_15.57.28.png",
  "expertise": ["62c65c85020d0f6ecbdc58a5", "62c6a24ada70f308968c2aa0", "62c5a427b1192a6c1e8d1569", "62c6a1ccda70f308968c2a9e", "62c6a1ffda70f308968c2a9f"],
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.users.insert({
  _id: new ObjectId("62c70dedf9c68f9f4d00cf41"),
  "firstNameEN": "Paramate",
  "lastNameEN": "Sarttarasupap",
  "firstNameTH": "ปรเมศวร์",
  "lastNameTH": "ศาสตรสุภาพ",
  "nickname": "เต๋า",
  "email": "tao@odds.team",
  "accountType": "OddsMember",
  "biography": "มีอะไรให้ผมช่วยบอกมาได้ครับ ถ้าผมตอบหรือช่วยไม่ได้ จะไปหาคนที่ตอบหรือช่วยได้มาให้ครับ",
  "team": "saksiam",
  "position": "ux, coach",
  "profileImageUrl": "https://cdn.discordapp.com/attachments/994530647395794994/994531106550456350/85A632F9-A76A-49AD-A36E-FAA414DE2B39.jpg",
  "expertise": ["62c6a3b067417941a44fc828", "62c6a3b967417941a44fc829", "62c6a3c667417941a44fc82a", "62c6a3ce67417941a44fc82b", "62c6a3d767417941a44fc82c"],
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.createCollection("expertise")
db.expertise.insert({
  _id: new new ObjectId("62c5a427b1192a6c1e8d1569"),
  skill: "TypeScript",
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})


db.expertise.insert({
  _id: new ObjectId("62c6a1ccda70f308968c2a9e"),
  skill: "Angular",
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.expertise.insert({
  _id: new ObjectId("62c6a24ada70f308968c2aa0"),
  skill: "Spring Boot",
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.expertise.insert({
  _id: new ObjectId("62c6a1ffda70f308968c2a9f"),
  skill: "เด็กถือกระเป๋าพี่แอร์",
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.expertise.insert({
  _id: new ObjectId("62c5b2004743e83ab91f2a26"),
  skill: "JavaScript",
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.expertise.insert({
  _id: new ObjectId("62c65c85020d0f6ecbdc58a5"),
  skill: "Java",
  "createdAt": "2022-07-07T12:55:24.614202",
  "updatedAt": "2022-07-07T12:55:24.614216"
})

db.expertise.insert({
  _id: new ObjectId("62c6a3b067417941a44fc828"),
  "skill": "ux",
  "createdAt": "2022-07-07T16:13:20.122",
  "updatedAt": "2022-07-07T16:13:20.122"
})

db.expertise.insert({
  _id: new ObjectId("62c6a3b967417941a44fc829"),
  "skill": "product discovery",
  "createdAt": "2022-07-07T16:13:29.671",
  "updatedAt": "2022-07-07T16:13:29.671"
})

db.expertise.insert({
  _id: new ObjectId("62c6a3c667417941a44fc82a"),
  "skill": "service design",
  "createdAt": "2022-07-07T16:13:42.438",
  "updatedAt": "2022-07-07T16:13:42.438"
})

db.expertise.insert({
  _id: new ObjectId("62c6a3ce67417941a44fc82b"),
  "skill": "design thinking",
  "createdAt": "2022-07-07T16:13:50.259",
  "updatedAt": "2022-07-07T16:13:50.259"
})

db.expertise.insert({
  _id: new ObjectId("62c6a3d767417941a44fc82c"),
  "skill": "storytelling",
  "createdAt": "2022-07-07T16:13:59.472",
  "updatedAt": "2022-07-07T16:13:59.472"
})

EOF

