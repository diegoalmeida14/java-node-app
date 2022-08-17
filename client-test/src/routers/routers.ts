import express from 'express'
const itensRouter = express.Router()
import axios from 'axios';

itensRouter.post('/search', async (req, res) => {
    console.log(req.body) 
    const response =  await  axios.post("http://localhost:8080/api/produto/search", req.body);
    return res.json(response.data);
});
itensRouter.post('/aluguel', async (req, res) => {
    console.log(req.body) 
    const response =  await  axios.post("http://localhost:8080/api/aluguel", req.body);
    return res.json(response.data);
});
itensRouter.put('/devolucao/:id', async (req, res) => {
    const id =  req.params.id;
    const response =  await  axios.post("http://localhost:8080/api/aluguel/devolucao/"+id);
    return res.json(response.data);
});
itensRouter.post('/venda', async (req, res) => {
    const response =  await  axios.post("http://localhost:8080/api/venda", req.body);
    return res.json(response.data);
});

// itensRouter.get('/alugar', (req, res) => {
//     res.send('Lê todos os itens')
// })
// itensRouter.get('/itens/:id', (req, res) => {
//     const id: number = +req.params.id
//     res.send(`Lê o item ${id}`)
// })
// itensRouter.put('/itens/:id', (req, res) => {
//     const id: number = +req.params.id
//     res.send(`Atualiza o item ${id}`)
// })
// itensRouter.delete('/itens/:id', (req, res) => {
//     const id: number = +req.params.id
//     res.send(`Apaga o item ${id}`)
// })
export default itensRouter