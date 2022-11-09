package masha.cha.dao;

import masha.cha.pojos.Person;


public interface PersonDao {

    public Person findById(Long id);

    public void savePerson(Person person);

    public void deletePerson(long id);

    public void deletePerson(Person person);

    public boolean toUpperCasePersonName(long id);

    public boolean toLowerCasePersonSurname(long id);

    public void saveAndDeletePerson(Person person);


}
