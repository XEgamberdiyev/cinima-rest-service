package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.model.Permission;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.PermissionRepo;
import uz.pdp.cinemarestservice.service.interfaces.PermissionService;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepo permissionRepo;

    @Override
    public ApiResponse getAllPermission() {
        List<Permission> permissions = permissionRepo.findAll();
        if (permissions != null) {
            return new ApiResponse("Success", true, permissions);
        }
        return new ApiResponse("Not found", false, null);
    }

    @Override
    public ApiResponse getPermissionById(Integer id) {
        Optional<Permission> optionalPermission = permissionRepo.findById(id);
        if (optionalPermission.isPresent()) {
            Permission permission = optionalPermission.get();
            return (new ApiResponse("Success", true, permission));
        }
        return (new ApiResponse("Not found", false, null));
    }

    @Override
    public ApiResponse addPermission(Permission permission) {
        Permission permission1 = new Permission();
        permission1.setName(permission.getName());
        permissionRepo.save(permission1);
        if (permission1 != null){
            return (new ApiResponse("Success", true, permission1));
        }
        return (new ApiResponse("not found", false, null));
    }

    @Override
    public ApiResponse updatePermission(Integer id, Permission permission) {
        Optional<Permission> optionalPermission = permissionRepo.findById(id);
        if (optionalPermission.isPresent()) {
            Permission permission1 = optionalPermission.get();
            permission1.setName(permission.getName());
            Permission save = permissionRepo.save(permission1);
            return (new ApiResponse("Success", true, save));
        }
        return (new ApiResponse("Not found", false, null));
    }

    @Override
    public ApiResponse deletePermission(Integer id) {
        Optional<Permission> optionalPermission = permissionRepo.findById(id);
        if (optionalPermission.isPresent()) {
            Permission permission = optionalPermission.get();
            permissionRepo.delete(permission);
            return (new ApiResponse("Success deleted", true));
        }

        return (new ApiResponse("Not found", false));
    }
}
