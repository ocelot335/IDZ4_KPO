/**
 * Контроллер для работы с блюдами.
 */
package com.example.hwww.controlers;
import com.example.hwww.exception.JWTException;
import com.example.hwww.exception.RequestException;
import com.example.hwww.exception.RoleException;
import com.example.hwww.repository.DishRepository;
import com.example.hwww.request.dish.DishCreateRequest;
import com.example.hwww.request.dish.DishDeleteRequest;
import com.example.hwww.request.dish.DishUpdateRequest;
import com.example.hwww.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1")
public class DishController {
    @Autowired
    private DishService dishService;
    /**
     * Создание блюда.
     * @param request запрос на создание блюда.
     * @return ответ сервера о результате создания блюда.
     */
    @PostMapping("/create_dish")
    public ResponseEntity<String> createDish(@RequestBody DishCreateRequest request) {
        try {
            dishService.createDish(request.getJWT(), request.getName(),
                    request.getDescription(), request.getPrice(), request.getQuantity(), request.isAvailable());
        } catch (JWTException | RoleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Блюдо успешно создано");
    }
    /**
     * Получение информации о блюде.
     * @param jwt токен доступа.
     * @param id идентификатор блюда.
     * @return ответ сервера с информацией о блюде.
     */
    @GetMapping("/read_dish")
    public ResponseEntity<String> readDish(@RequestParam("JWT") String jwt,
                                           @RequestParam("id") int id) {
        String info;
        try {
            info = dishService.readDish(jwt, id);
        } catch (JWTException | RequestException | RoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Информация о блюде: " + info);
    }
    /**
     * Обновление информации о блюде.
     * @param request запрос на обновление информации о блюде.
     * @return ответ сервера о результате обновления информации о блюде.
     */
    @PutMapping("/update_dish")
    public ResponseEntity<String> updateDish(@RequestBody DishUpdateRequest request) {
        try {
            dishService.updateDish(request.getJWT(), request.getId(), request.getName(),
                    request.getDescription(), request.getPrice(), request.getQuantity(),
                    request.isAvailable());
        } catch (JWTException | RequestException | RoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Информация о блюде успешно обновлена");
    }
    /**
     * Удаление блюда.
     * @param request запрос на удаление блюда.
     * @return ответ сервера о результате удаления блюда.
     */
    @DeleteMapping("/delete_dish")
    public ResponseEntity<String> deleteDish(@RequestBody DishDeleteRequest request) {
        try {
            dishService.deleteDish(request.getJWT(), request.getId());
        } catch (JWTException | RequestException | RoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Блюдо успешно удалено");
    }
}