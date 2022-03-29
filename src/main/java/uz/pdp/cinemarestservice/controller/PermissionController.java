package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Permission;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.PermissionServiceImpl;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    PermissionServiceImpl permissionService;

    @GetMapping
    public HttpEntity<?> getAllPermission(){
        ApiResponse allPermission = permissionService.getAllPermission();
        return ResponseEntity.status(allPermission.isSuccess()? 200: 404).body(allPermission);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPermissionById(@PathVariable Integer id){
        ApiResponse permissionById = permissionService.getPermissionById(id);
        return ResponseEntity.status(permissionById.isSuccess()?200: 404).body(permissionById);
    }

    @PostMapping
    public HttpEntity<?> addPermission(@RequestBody Permission permission){
        ApiResponse addResponse = permissionService.addPermission(permission);
        return ResponseEntity.status(addResponse.isSuccess()?200:404).body(addResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updatePermission(@PathVariable Integer id,
                                          @RequestBody Permission permission){
        ApiResponse updatePermission = permissionService.updatePermission(id, permission);
        return ResponseEntity.status(updatePermission.isSuccess()?200:404).body(updatePermission);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletepermission(@PathVariable Integer id){
        ApiResponse deletePermission = permissionService.deletePermission(id);
        return ResponseEntity.status(deletePermission.isSuccess()?200: 404).body(deletePermission);
    }
}
