package AppRev1.App1.controller;

import AppRev1.highLevelApp.persistence.entity.*;
import AppRev1.highLevelApp.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private AdmissionService as;

    @Autowired
    private OperationService os;

    @Autowired
    private OperatorService opers;

    @Autowired
    private PersonService ps;

    @Autowired
    private RoleService rs;

    @RequestMapping(method = RequestMethod.GET, value = "/Hello")
    public String greeter(){
//        init();
        return "hello";
    }
    public void init(){
        MOperation op1 = new MOperation();
        op1.setText("1sddfsj");
        os.addOperation(op1);
        MOperation op2 = new MOperation();
        op2.setText("2sddfsj");
        os.addOperation(op2);
        MOperation op3 = new MOperation();
        op3.setText("3sddfsj");
        os.addOperation(op3);

        List<MOperation> opList = new ArrayList<>();
        opList.add(os.getOperation(1l));
        opList.add(os.getOperation(2l));

        List<MOperation> opList2 = opList.subList(0,1);

        Role role1 = new Role("ADMIN");
        rs.addRole(role1);
        Role role2 = new Role("USER");
        rs.addRole(role2);
        List<Role> roleList1 = new ArrayList<>();
        roleList1.add(rs.getRole(1l));
        roleList1.add(rs.getRole(2l));

        List<Role> roleList2 = roleList1.subList(0,1);

        Person per1 = new Person();
        per1.setLogin("per1");
        per1.setRolesList(roleList1);
        ps.addPerson(per1);
        Person per2 = new Person();
        per2.setLogin("per2");
        per2.setRolesList(roleList2);
        ps.addPerson(per2);
        Person per3 = new Person();
        per3.setLogin("per3");
        ps.addPerson(per3);

        MOperator oper1 = new MOperator(ps.getPerson(1l));
        oper1.setOperationsList(opList);
        opers.addOperator(oper1);
        MOperator oper2 = new MOperator();
        oper2.setOperationsList(opList2);
        opers.addOperator(oper2);
        MOperator oper3 = new MOperator();
        opers.addOperator(oper3);

        Admission ad1 = new Admission(os.getOperation(1l),opers.getOperator(2l),
                ps.getPerson(3l), new Timestamp(new Date().getTime()));
        as.addAdmission(ad1);
        Admission ad2 = new Admission();
        ad2.setPerson(ps.getPerson(2l));
        as.addAdmission(ad2);

        Person ers = ps.getPerson(3l);
        ers.setLogin("changed");
        ps.updatePerson(ers);
    }
}
