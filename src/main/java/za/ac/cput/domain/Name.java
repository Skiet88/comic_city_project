package za.ac.cput.domain;

// Mlungisi L. Mbuyazi
// 221164014
// https://github.com/Skiet88/comic__city_project


import java.util.Objects;
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    protected Name() {
    }

    private Name(NameBuilder n) {
        this.firstName = n.firstName;
        this.middleName = n.middleName;
        this.lastName = n.lastName;

    }



    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }




    @Override
    public String toString() {
        return "Name{" +

                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(middleName, name.middleName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    public static class NameBuilder{
        private String firstName;
        private String middleName;
        private String lastName;


        public NameBuilder(){


        }

        public NameBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public NameBuilder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }


        public NameBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public NameBuilder copy(Name n) {
            this.firstName = n.firstName;
            this.middleName = n.middleName;
            this.lastName = n.lastName;


            return this;
        }

        public Name build(){

            return new Name(this);
        }
    }
}
