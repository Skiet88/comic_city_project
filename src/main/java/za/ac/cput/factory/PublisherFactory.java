package za.ac.cput.factory;

import org.springframework.jmx.access.InvalidInvocationException;
import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Publisher;
import za.ac.cput.util.Helper;

import java.util.List;

public class PublisherFactory {
    public static Publisher buildPublisher(Long publisherId, String name, int yearFounded){
        if(publisherId<=0 || Helper.isStringNullorEmpty(name) || Helper.isInvalidInt(yearFounded)
        ){
            return null;

        }
        return new Publisher.PublisherBuilder().setPublisherId(publisherId)
                .setName(name)
                .setYearFounded(yearFounded)
                .build();
    }
    public static Publisher buildPublisher(String name, int yearFounded){
        if( Helper.isStringNullorEmpty(name) || Helper.isInvalidInt(yearFounded)
        ){
            return null;

        }
        return new Publisher.PublisherBuilder()
                .setName(name)
                .setYearFounded(yearFounded)
                .build();
    }
}
