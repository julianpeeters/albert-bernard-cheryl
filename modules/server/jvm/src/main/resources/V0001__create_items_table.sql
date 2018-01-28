CREATE TABLE items (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(191) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL,
  last_updated_at TIMESTAMP
);