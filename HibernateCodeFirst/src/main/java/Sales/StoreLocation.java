package Sales;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "store_locations")
public class StoreLocation {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation() {
    }

    public StoreLocation(String locationName) {
        this.locationName = locationName;
        this.sales = new HashSet<>();
    }
}
