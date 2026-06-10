CREATE TABLE account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    display_name VARCHAR(255),
    profile_text TEXT,
    image_path VARCHAR(255)
);

CREATE TABLE blog (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    content TEXT,
    category VARCHAR(255),
    created_at DATETIME,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES account(id)
);
