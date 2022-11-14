package masha.cha.dao;

import masha.cha.pojos.Person;


public interface PersonDao {

    public Person findById(Long id);

    public void savePerson(Person person);

    public Person savePersonWithTrigger(Person person);

    public Person deletePerson(long id);

    public void updatePerson(Person person);

    public Person updatePersonWithTrigger(Person person);

    public Person loadById(Long id);

    public Person getById(Long id);

    public void deletePerson(Person person);

    public void saveAndDeletePerson(Person person);





}
