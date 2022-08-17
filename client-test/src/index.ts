import express from 'express'
import itensRouter from './routers/routers'

// Porta do servidor
// App Express
const app = express()
app.use(express.json())
app.use(express.urlencoded({ extended: true }))

app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.get('/', (req, res) => {
    res.send('Bem-vindo!')
})
app.use('/api', itensRouter);

app.use((req, res) => {
    res.status(404)
})
// Inicia o sevidor
app.listen(4000, () => {
    console.log(`Servidor rodando com sucesso localhost:4000`)
})