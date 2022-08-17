package me.aluga.inventory.repository;


    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import me.aluga.inventory.entity.Aluguel;;

    @Repository
    public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {
        

    }
