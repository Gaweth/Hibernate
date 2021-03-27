package pl.academy.database.utils.main;

import pl.academy.database.dao.RunDao;
import pl.academy.database.dao.RunMemberDao;
import pl.academy.database.daoimpl.RunDaoImpl;
import pl.academy.database.daoimpl.RunMemberDaoImpl;
import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RunCenter {

    public static void main(String[] args) {
        int selected;
        Scanner scanner = new Scanner(System.in);
        do {
            log("[1] Dopisz nowy bieg");
            log("[2] Wyświetl biegi");
            log("[3] Usuń bieg");
            log("[4] Wyświetl listę uczestników dla biegu");
            log("[5] Dopisz uczestnika do biegu");
            log("[6] Usuń uczestnika");
            log("[7] Wyszukaj uczestnika po numerze startowym");



            log("[0] Wyjście");
            selected = scanner.nextInt();

            switch (selected) {
                case 1: handleAddNewRun(); break;
                case 2: handleShowAllRuns(); break;
                case 3: handleDeleteRun(); break;
                case 4: handleShowAllRunMembers(); break;
                case 5: handleAddRunMember(); break;
                case 6: handleDeleteRunMember(); break;
                case 7: handleFindRunMemeberByStartNumber(); break;
            }
        } while (selected != 0);
    }

    private static void handleFindRunMemeberByStartNumber() {

    }

    private static void handleDeleteRunMember() {
    }

    private static void handleShowAllRunMembers() {
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        List<RunMember> list = runMemberDao.findAll();

        log("Lista uczestnikow");
        log("---------------");
        for(RunMember run : list) {
            log(
                    run.getId() + " " +
                            run.getName()  + " "

            );
        }
        log("----------------");
    }


    private static void handleAddRunMember() {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new RunDaoImpl();

        log("podaj ip biegu");
        long runId = scanner.nextLong();

        Run run = runDao.findById(runId);
        log("uczestnicy to " + run.getName());
        for (RunMember member : run.getMembers()) {
            log(member.getId()) + member.getName() + " " +member.getStartNumber();
        }



    }

    private static void handleDeleteRun() {

        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new RunDaoImpl();
       RunMemberDao memberDao = new RunMemberDaoImpl();

        log("Podaj nazwę biegu");
        long runId = scanner.nextLong();
        Run run = runDao.findById(runId);

        for (RunMember member : run.getMembers())
        {
            memberDao.deleteById(member.getId());
        }

        runDao.deleteById(runId);

    }

    private static void log(String text) {
        System.out.println(text);
    }

    private static void handleShowAllRuns() {
        RunDao runDao = new RunDaoImpl();
        List<Run> list = runDao.findAll();

        log("Lista biegów");
        log("---------------");
        for(Run run : list) {
            log(
                    run.getId() + " " +
                            run.getName()  + " " +
                            run.getMembersLimit()
            );
        }
        log("----------------");
    }

    private static void handleAddNewRun() {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new RunDaoImpl();

        Run run = new Run();

        log("Podaj nazwę biegu");
        run.setName(scanner.nextLine());

        log("Podaj limit uczestników");
        run.setMembersLimit(scanner.nextInt());

        log("Podaj dystans");
        run.setDistance(scanner.nextInt());

        runDao.save(run);
    }
    
    
}
