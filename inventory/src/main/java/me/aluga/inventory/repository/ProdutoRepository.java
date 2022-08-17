package me.aluga.inventory.repository;


    import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
    import me.aluga.inventory.entity.Produto;;

    @Repository
    public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
        
     
    @Query(value="SELECT * FROM produto p WHERE p.nome = :nome " ,   nativeQuery = true)
    List<Produto> findByName(@Param("nome") String nome);
    
    @Query(value="SELECT * from produto p WHERE p.tipo = :tipo" ,   nativeQuery = true)
    List<Produto> findByTipo(@Param("tipo") String tipo);


    @Query(value="SELECT * from produto p WHERE p.nome = :nome AND p.tipo = :tipo ",   nativeQuery = true)
    List<Produto> findByProduto(@Param("nome") String nome, @Param("tipo") String tipo);


    @Query(value="SELECT * from produto p WHERE p.id in (:ids)",   nativeQuery = true)
    List<Produto> findByIds(@Param("ids") List<Integer> ids);

    
    
      

    }
