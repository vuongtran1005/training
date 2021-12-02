DROP TABLE IF EXISTS tbl_product;

CREATE TABLE tbl_product (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          description TEXT,
                          import_price DECIMAL(13,2),
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP,
                          deleted_at TIMESTAMP,
                          created_by VARCHAR(255),
                          updated_by VARCHAR(255),
                          deleted_by VARCHAR(255),
                          is_deleted ENUM('TRUE', 'FALSE'),
                          status ENUM('ACTIVE', 'DISABLE')

);