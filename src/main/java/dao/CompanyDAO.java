package dao;

import configuration.SessionFactoryUtil;
import dto.ClientDTO;
import dto.CompanyDTO;
import dto.DriverDTO;
import entity.Client;
import entity.Company;
import entity.Driver;
import entity.Transport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyDAO {

    public static void AddClientToCompany(long companyID, Client client){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();

            Company company = CompanyDAO.getCompany(companyID);
            session.evict(company);
            session.refresh(company);

            session.evict(client);
            session.refresh(client);

            company.getClients().add(client);
            client.getCompanies().add(company);

            session.saveOrUpdate(company);
            session.saveOrUpdate(client);


            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void RemoveClientFromCompany(long companyID, long clientID){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();

            Company company = CompanyDAO.getCompany(companyID);
            Client client = ClientDAO.getClient(clientID);
            session.evict(company);
            session.refresh(company);

            session.evict(client);
            session.refresh(client);

            company.getClients().remove(client);
            client.getCompanies().remove(company);

            session.saveOrUpdate(company);
            session.saveOrUpdate(client);


            transaction.commit();
        }

    }

    public static void createCompany(String name, String address){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            Company company = new Company(name, address);
            session.save(company);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveCompany(entity.Company company){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.save(company);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveOrUpdateCompany(Company company){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.saveOrUpdate(company);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void deleteCompany(Company company){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }

    public static Company getCompany(long id) {
        Company company;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, id);

            transaction.commit();

        }
        return company;
    }

    public static Set<DriverDTO> getCompanyDriversDTO(long id){

        Set<DriverDTO> drivers;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            drivers = session.createQuery("select new dto.DriverDTO(e.id, e.name, e.qualification, e.salary) from Driver e"
            + " join e.company c " + "where c.id = :id", DriverDTO.class)
                            .setParameter("id", id).getResultList().stream().collect(Collectors.toSet());

            transaction.commit();
        }
        return drivers;

    }

    public static Set<Driver> getCompanyDrivers(long id){
        Company company;
        //Set<Driver> drivers;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.createQuery("select e from Company e"
                            + " join fetch e.drivers " + "where e.id = :id", Company.class)
                    .setParameter("id", id).getSingleResult();

            transaction.commit();
        }
        return company.getDrivers();

    }

    public static Set<ClientDTO> getCompanyClientsDTO(long id){

        Set<ClientDTO> clients;

        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            clients = session.createQuery("select new dto.ClientDTO(e.id, e.name, e.phoneNumber) from Client e"
             + " join e.companies c " + "where c.id =:id", ClientDTO.class)
                            .setParameter("id", id).getResultList().stream().collect(Collectors.toSet());


            transaction.commit();
        }
        return clients;
    }

    public static Set<Client> getCompanyClients(long id){
        Company company;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            company = session.createQuery("select e from Company e "
            + "join fetch e.clients " + "where e.id = :id", Company.class).setParameter("id", id).getSingleResult();

            transaction.commit();
        }
        return company.getClients();
    }

    public static void WriteFile(String FileName, long id){

        Company company = CompanyDAO.getCompany(id);
        try (FileWriter fout = new FileWriter(FileName, false)){
            fout.append(company.getName() + ": " + CompanyDAO.getCompany(id) + System.lineSeparator() + company.getName() + " Income: " + CompanyDAO.CalculateCompanyIncome(id) + System.lineSeparator());
            fout.append("Employees of " + company.getName() + ": " + System.lineSeparator() + CompanyDAO.getCompanyDriversDescDTO(id));
            fout.append(System.lineSeparator() + company.getName() + " Clients: " + System.lineSeparator() + CompanyDAO.getCompanyClientsDTO(id));
            fout.append(System.lineSeparator() + company.getName() + " Total Income from transports: " + CompanyDAO.TotalSumOfCompletedTransportsofCompany(id) + System.lineSeparator());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void ReadFromFile(String FilePath){

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BigDecimal CalculateCompanyIncome(long id) {
        BigDecimal Income = BigDecimal.valueOf(0);
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);

            Subquery<Client> subquery = query.subquery(Client.class);
            Root<Client> subRoot = subquery.from(Client.class);
            Join<Client, Company> subCompany = subRoot.join("companies");
            subquery.select(subRoot);
            subquery.where(builder.equal(subCompany.get("id"), id));

            Root<Transport> transport = query.from(Transport.class);
            Join<Transport, Client> client = transport.join("client", JoinType.INNER);

            query.select(builder.sum(transport.get("price")));
            query.where(client.in(subquery), builder.equal(transport.get("isPaid"), true));

            Income = session.createQuery(query).getSingleResult();
            Company company = CompanyDAO.getCompany(id);
            if(Income == null) {
                Income = company.getIncome();
            } else{
                company.setIncome(Income);
            }
            CompanyDAO.saveOrUpdateCompany(company);

            transaction.commit();
            System.out.println("Income of " + company.getName() + ": ");

        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return Income;
    }

    public static BigDecimal TotalSumOfCompletedTransportsofCompany(long id) {
        BigDecimal TotalPrice = BigDecimal.valueOf(0);
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            Company company = CompanyDAO.getCompany(id);

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);

            Subquery<Client> subquery = query.subquery(Client.class);
            Root<Client> subRoot = subquery.from(Client.class);
            Join<Client, Company> subCompany = subRoot.join("companies");
            subquery.select(subRoot);
            subquery.where(builder.equal(subCompany.get("id"), id));

            Root<Transport> transport = query.from(Transport.class);
            Join<Transport, Client> client = transport.join("client", JoinType.INNER);

            query.select(builder.sum(transport.get("price")));
            query.where(client.in(subquery), builder.equal(transport.get("isPaid"), true));

            TotalPrice = session.createQuery(query).getSingleResult();

            //System.out.println("Total sum of completed transports of " + company.getName() + ": ");

        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return TotalPrice;
    }

    public static List<DriverDTO> getCompanyDriversDescDTO(long id){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {


            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DriverDTO> criteria = builder.createQuery(DriverDTO.class);
            Root<Driver> root = criteria.from(Driver.class);

            criteria.where(builder.equal(root.get("company"), id));
            criteria.orderBy(builder.desc(root.get("salary")));
            criteria.select(builder.construct(DriverDTO.class, root.get("id"), root.get("name"), root.get("qualification"), root.get("salary")));
            List<DriverDTO> drivers = session.createQuery(criteria).getResultList();


            return drivers;
        }
    }

    public static List<CompanyDTO> getCompaniesSortedByIncomeDTO(){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CompanyDTO> query = builder.createQuery(CompanyDTO.class);
            Root<Company> root = query.from(Company.class);

            //query.select(builder.desc(root.get("company")))
            query.select(builder.construct(CompanyDTO.class, root.get("id"), root.get("name"), root.get("address"), root.get("income")));
            query.orderBy(builder.desc(root.get("income")));

            List<CompanyDTO> companies = session.createQuery(query).getResultList();
            return companies;

        }

    }

}
