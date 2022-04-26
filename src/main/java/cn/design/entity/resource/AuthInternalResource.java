package cn.design.entity.resource;

/**
 * 【权限服务内部资源】<p>
 * 为了实现权限的功能，对自身权限体系的实现需要依赖的资源。<p>
 * 举例子：<p>&emsp;
 * 1. {@link cn.design.entity.user.User 用户}<p>&emsp;
 * 2. {@link cn.design.entity.role.Role 角色}<p>&emsp;
 * 3. {@link cn.design.entity.tenant.Tenant 租户}<p>&emsp;
 * 4. {@link cn.design.entity.permission.PermissionTemplate 权限模板}
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/3/21 11:42
 */
public interface AuthInternalResource extends Resource {
}
