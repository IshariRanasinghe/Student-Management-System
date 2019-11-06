package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.RoleBO;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.RoleDAO;
import lk.ijse.dep.sms.dto.RoleDTO;
import lk.ijse.dep.sms.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleBOImpl implements RoleBO {
    private RoleDAO roleDAO = DAOFactory.getInstance().getDAO(DAOTypes.ROLE);

    @Override
    public List<RoleDTO> getRoles() throws Exception {
        List<Role> allRoles = roleDAO.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role:allRoles) {
            roleDTOS.add(new RoleDTO(role.getId(),role.getRole()));
        }
        return roleDTOS;
    }

    @Override
    public List<String> roleswithoutAdmin() throws Exception {
        return roleDAO.rolesWithoutAdmin();
    }

    @Override
    public Integer getRoleId(String selectedRole) throws Exception {
        return roleDAO.getRoleId(selectedRole);
    }
}
