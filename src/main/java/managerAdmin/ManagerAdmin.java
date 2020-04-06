package managerAdmin;

import managerAdmin.command.CreationHandler;
import managerAdmin.command.CreationSubscription;
import managerAdmin.command.UpdatingHandler;
import managerAdmin.command.UpdatingSubscription;
import reportService.repository.ReportRepository;

public class ManagerAdmin {

    public void execute (CreationSubscription subscription) {
        CreationHandler.execute(subscription);
        ReportRepository.getInstance().createSubscription(subscription);
    }

    public void execute (UpdatingSubscription subscription) {
        UpdatingHandler.execute(subscription);
    }
}
