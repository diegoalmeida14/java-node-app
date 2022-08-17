package me.aluga.inventory.repository;


    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import me.aluga.inventory.entity.Cliente;;

    @Repository
    public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
        

    }
