import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeBody {
    private String name;
    private String salary;
    private String age;
}
