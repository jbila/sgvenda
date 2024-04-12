DELIMITER //
CREATE PROCEDURE UpdateStockQty(IN product_id INT, IN quantity_sold INT)
BEGIN
    DECLARE current_qty INT;
    
    -- Get the current quantity in stock for the product
    SELECT qty INTO current_qty FROM tbl_stock WHERE id_producto = product_id;
    
    -- Check if there is enough quantity in stock to fulfill the sale
    IF current_qty >= quantity_sold THEN
        -- Update the quantity in stock
        UPDATE tbl_stock SET qty = current_qty - quantity_sold WHERE id_producto = product_id;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient stock for the sale';
    END IF;
END //

DELIMITER ;
call UpdateStockQty(5,12);