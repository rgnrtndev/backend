# RCC Back End
This is project for RCC Church

### How to running:
- mvn clean install -DskipTests
- run docker compose to setup databases
  - docker-compose up -d
- mvn springboot:run
- access document
  - http://localhost:8081/rcc/swagger-ui/index.html

### running docker for redis
- docker pull redis
- docker run --name redis-container -d -p 6379:6379 -v redis-data:/data redis

### 16 Dec 2024
- add README.md
- add swagger

## Entity
- ** Routing Api Gateway **
- Add entity for Gallery
- Add entity for Announcement
- Add entity for Article
- Add entity for Board
- Add entity for Category
- Add entity for Role
- Add entity for Slider

### Developer in Charge:
- Lerry
- Steven
- Argha
- Regi