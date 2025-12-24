package lab3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "space_objects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type; // Зірка, Планета, Супутник
    private Long parentId; // ID батьківського об'єкта
}