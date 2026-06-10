INSERT INTO account (username, password, display_name)
VALUES ('test', '{noop}password', 'テストユーザー');

INSERT INTO blog (title, content, category, created_at, author_id)
VALUES ('サンプル記事', 'これはサンプルです。', '日記', NOW(), 1);