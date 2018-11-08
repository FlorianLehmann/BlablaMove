package arquillian.fr.unice;

import fr.unice.RegistryModifier;
import fr.unice.Tracker;
import fr.unice.model.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RouteRelocationRegisterTest {

    @EJB
    private RegistryModifier registryModifier;

    @EJB
    private Tracker tracker;

    @PersistenceContext
    private EntityManager entityManager;

    // Classes to package into a deployable unit used to run the test
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(RegistryModifier.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");

    }

    @Before
    public void defineContext() {
        Query query = entityManager.createQuery("Delete from Relocation r");
        query.executeUpdate();
    }

    @Transactional(TransactionMode.COMMIT)
    @Test
    public void shouldNotContainAnyEnitityByDefault() {

        List<Relocation> storedRelocations = findRelocations();

        assertTrue(storedRelocations.isEmpty());
        entityManager.clear();
    }

    @Transactional(TransactionMode.COMMIT)
    @Test
    public void shouldCreateARelocation() {

        Address addressDeparture = new Address("06410", "930 Route des Colles", "Biot");
        Address addressArrival = new Address("13009", "163 Avenue de Luminy", "Marseille");
        User user = new User("Florian");
        Date startDate = new Date(11, 11, 2018);
        Date endDate = new Date(11, 11, 2018);

        registryModifier.addRelocation(addressArrival, addressDeparture, startDate, endDate, new Dimension(), user);

        List<Relocation> storedRelocations = findRelocations();

        assertEquals(1, storedRelocations.size());

    }

    @Transactional(TransactionMode.COMMIT)
    @Test
    public void shouldGetEveryRelocationRelatedToUser() {

        Address addressDeparture = new Address("06410", "930 Route des Colles", "Biot");
        Address addressArrival = new Address("13009", "163 Avenue de Luminy", "Marseille");
        User user = new User("Florian");
        Date startDate = new Date(11, 11, 2018);
        Date endDate = new Date(11, 11, 2018);

        registryModifier.addRelocation(addressArrival, addressDeparture, startDate, endDate, new Dimension(), user);

        List<Relocation> storedRelocations = tracker.getRelocations(user);

        assertEquals(1, storedRelocations.size());

    }

    private List<Relocation> findRelocations() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Relocation> criteria = builder.createQuery(Relocation.class);
        Root<Relocation> root = criteria.from(Relocation.class);

        criteria.select(root);
        TypedQuery<Relocation> query = entityManager.createQuery(criteria);

        return query.getResultList();

    }


}
