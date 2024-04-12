DELIMITER //
CREATE TRIGGER AfterInsertItemPedido
AFTER INSERT ON tbl_items_pedido
FOR EACH ROW
BEGIN
    DECLARE product_id INT;
    DECLARE quantity_sold INT;
    -- Get the product_id and quantity_sold from the inserted row
    SET product_id = NEW.id_producto;
    SET quantity_sold = NEW.quantidade;
    -- Call the UpdateStockQty procedure
    CALL UpdateStockQty(product_id, quantity_sold);
END //
DELIMITER ;