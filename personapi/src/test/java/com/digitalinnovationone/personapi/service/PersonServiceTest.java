package com.digitalinnovationone.personapi.service;

import com.digitalinnovationone.personapi.dto.request.PersonDTO;
import com.digitalinnovationone.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personapi.entity.Person;
import com.digitalinnovationone.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.digitalinnovationone.personapi.utils.PersonUtils.createFakeDTO;
import static com.digitalinnovationone.personapi.utils.PersonUtils.createdFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSendMessage(){
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createdFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
        MessageResponseDTO expectedSucessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO sucessMessage= personService.createPerson(personDTO);

        assertEquals(expectedSucessMessage,sucessMessage);

    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
