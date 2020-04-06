package turnstile;

import reportService.repository.ReportRepository;
import turnstile.command.Enter;
import turnstile.command.EnterHandler;
import turnstile.command.Exit;
import turnstile.command.ExitHandler;
import turnstile.query.EnterChecker;
import turnstile.query.EnterCheckerHandler;

public class Turnstile {

    public boolean execute(EnterChecker enter) {
        return EnterCheckerHandler.execute(enter);
    }

    public void execute(Enter enter) {

        EnterHandler.execute(enter);
        ReportRepository.getInstance().saveEnter(enter);
    }

    public void execute(Exit exit) {

        ExitHandler.execute(exit);
        ReportRepository.getInstance().saveExit(exit);
    }

}
