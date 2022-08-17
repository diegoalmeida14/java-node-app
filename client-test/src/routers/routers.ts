import express from 'express'
const itensRouter = express.Router()
import axios from 'axios';

itensRouter.post('/search', async (req, res) => {
    console.log(req.body) 
    const response =  await  axios.post("http://localhost:8080/api/produto/search", req.body);
    return res.json(response.data);
});
itensRouter.post('/aluguel', async (req, res) => {
    try{
        const response =  await  axios.post("http://localhost:8080/api/aluguel", req.body);
        return res.json(response.data);
    }catch(err:any){
        if(err.response.status == 404){
            return res.json({erro:"quantidade insuficiente"});
        }
        return res.json(err);
    }
    
});
itensRouter.put('/devolucao/:id', async (req, res) => {
    const id =  req.params.id;
    const response =  await  axios.put("http://localhost:8080/api/aluguel/devolucao/"+id);
    return res.json(response.data);
});
itensRouter.post('/venda', async (req, res) => {
    try{
        const response =  await  axios.post("http://localhost:8080/api/venda", req.body);
        return res.json(response.data);
    }catch(err:any){
        if(err.response.status == 404){
            return res.json({erro:"quantidade insuficiente"});
        }
        return res.json(err);
    }
});
export default itensRouter