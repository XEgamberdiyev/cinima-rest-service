package uz.pdp.cinemarestservice.service.interfaces;


import uz.pdp.cinemarestservice.model.Permission;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;

public interface PermissionService {

    public ApiResponse getAllPermission();

    public ApiResponse getPermissionById(Integer id);

    public ApiResponse addPermission(Permission permission);

    public ApiResponse updatePermission(Integer id, Permission permission);

    public ApiResponse deletePermission(Integer id);

}
