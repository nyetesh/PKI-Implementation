import com.example.studentapp.dto.AdmissionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class SchoolControllerTest extends ControllerTest{

    public static void main(String[] args) {
        SchoolControllerTest invoker = new SchoolControllerTest();
        invoker.admit();

    }

    public void admit() {
        String webUrl = BASE_URL + "student/admit";

        AdmissionRequest admissionRequest= new AdmissionRequest();
        admissionRequest.setStudentName("Nitesh");
        admissionRequest.setPhoneNumber("981234546");
        admissionRequest.setGuardiansName("Ram");
        admissionRequest.setGuardiansPhoneNumber("01424305");

        HttpEntity<AdmissionRequest> request = new HttpEntity<>(admissionRequest, headers);

        String result = restTemplate.postForObject(webUrl, request, String.class);

        log.info("Result: " + result);
    }
}
