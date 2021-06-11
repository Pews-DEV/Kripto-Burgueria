import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Banco {
    public String[] data = {
        "Pão Francês", "Pão Carteira", "Pão de Hambúrguer", "Pão Árabe",
        "Coalho", "Minas", "Muçarela", "Cream Cheese", "Gorgonzola",
        "Mortadela", "Apresuntado", "Bacon", "Presunto", "Pepperoni", "Salame",
        "Cebola", "Pimentão", "Tomate", "Alface",
        "Maionese", "Ketchup", "Maionese Temperada", "Molho Tártato", "Barbecue",
        "Batata Palha", "Ovo"
    };
    public Float[] data_precos = {
        0.25f, 0.30f, 0.70f, 1.30f,
        1.50f, 1.80f, 2.00f, 3.00f, 3.50f,
        0.50f, 1.00f, 1.30f, 1.60f, 1.80f, 2.00f,
        0.30f, 0.45f, 0.50f, 0.50f,
        0.50f, 0.50f, 0.70f, 1.00f, 1.50f,
        1.00f, 1.00f
    };

    public ArrayList<Integer> pedidos_id;
    
    public Banco(){
        this.start();
    }

    public Float getValorPedido(Integer pedido){
        String select_pedidos = "SELECT * FROM pedidos WHERE id = " + pedido;

        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:storage.db")){

            Statement session = conn.createStatement();
            ResultSet instances = session.executeQuery(select_pedidos);
            return instances.getFloat("valor_total");

        } catch(SQLException e){
            System.out.println(e);
        }

        return 0f;
    }

    public ArrayList<Integer> getPedidos(){
        pedidos_id = new ArrayList<Integer>();

        String select_pedidos = "SELECT * FROM pedidos";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:storage.db")){
            Statement session = conn.createStatement();

            ResultSet instances = session.executeQuery(select_pedidos);
            while (instances.next()){
                pedidos_id.add(instances.getInt("id"));
            };

        } catch(SQLException e){
            System.out.println(e);
        }

        return pedidos_id;
    }

    public void adicionar_pedido(HashMap<String, ArrayList<String>> pedido){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:storage.db")){
            Statement session = conn.createStatement();
            Float valor_total = 0f;

            // Calculando valor total
            for (String key: pedido.keySet()){

                Float valor = Float.parseFloat(pedido.get(key).get(0).toString());
                Integer quantidade = Integer.parseInt(pedido.get(key).get(1).toString());

                valor_total = valor_total + (valor * quantidade);
            }

            // Criando pedido
            String insert_pedido = "INSERT INTO pedidos(valor_total)" +
                                   "VALUES(" + valor_total + ")";
            Integer id = session.executeUpdate(insert_pedido);

            // Adicionando ingredientes do pedido
            for (String key: pedido.keySet()){
                String slug = key.replace(" ", "_");

                String get_ingrediente = "SELECT * FROM ingredientes WHERE slug = '" + slug + "'";
                Integer ingrediente_id = session.executeQuery(get_ingrediente).getInt("id");

                Integer quantidade = Integer.parseInt(pedido.get(key).get(1).toString());

                String insert_pedido_ingredientes = "INSERT INTO pedidos_ingredientes(pedido_id, ingrediente_id, quantidade)" +
                                                    "VALUES(" + id + ", " + ingrediente_id + ", " + quantidade + ")";
                session.executeUpdate(insert_pedido_ingredientes);
            }

        } catch(SQLException e){
            System.out.println(e);
        }
    }

    public void start(){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:storage.db")){
            Statement session = conn.createStatement();


            String create_pedidos = "CREATE TABLE IF NOT EXISTS pedidos (" + 
                                    "id INTEGER PRIMARY KEY NOT NULL," +
                                    "valor_total FLOAT NOT NULL)";

            session.execute(create_pedidos);


            String create_itens = "CREATE TABLE IF NOT EXISTS ingredientes (" + 
                                  "id INTEGER PRIMARY KEY NOT NULL," +
                                  "nome VARCHAR(40) NOT NULL," + 
                                  "slug VARCHAR(40) NOT NULL UNIQUE," + 
                                  "preco FLOAT NOT NULL)";
            session.execute(create_itens);

            String create_itens_pedidos = "CREATE TABLE IF NOT EXISTS pedidos_ingredientes (" +
                                          "id INTEGER PRIMARY KEY NOT NULL," +
                                          "pedido_id INTEGER REFERENCES pedidos(id)," +
                                          "ingrediente_id INTEGER REFERENCES ingredientes(id)," +
                                          "quantidade INTEGER NOT NULL)";

            session.execute(create_itens_pedidos);

            for (int i = 0; i < data.length; i++){

                String slug = data[i].toString().replace(" ", "_");
                String nome = data[i].toString();
                Float preco = data_precos[i];

                String insert_ingredientes = "INSERT INTO ingredientes(nome, slug, preco) " +
                                             "VALUES('" + nome + "', '" + slug + "', " + preco + ")"; 
                session.executeUpdate(insert_ingredientes);
            }

        } catch(SQLException e){
        }
    }
    
}
