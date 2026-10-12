# HYPERDUC! – Plataforma de Gestão de Projetos Colaborativos

Projeto da disciplina **Design de Software (RA2)** – PUCPR, Engenharia de Software, 2026.
Equipe: Amanda Freire e Valentina Lago.

Evolução do projeto de POO com a aplicação de quatro padrões GoF:

| Padrão | Onde está | Para quê |
|---|---|---|
| **Singleton** | `GerenciadorArquivo` | Ponto único de leitura/gravação dos arquivos `.dat` |
| **Template Method** | `RepositorioArquivo<T>` + `ProjetoRepositorio`, `AtividadeRepositorio`, `DenunciaRepositorio`, `ColaboradorRepositorio` | Algoritmo de CRUD escrito uma vez; subclasses só informam arquivo, ID e validação |
| **State** | `EstadoAtividade`, `EstadoProjeto`, `EstadoDenuncia` (estados concretos como classes internas) | Ciclo de vida de atividades, projetos e denúncias, bloqueando transições inválidas |
| **Memento** | `Atividade.Memento`, `Projeto.Memento`, `Denuncia.Memento` + `Historico<M>` | Botão "Desfazer Última Alteração" nas telas de atualização |

## Ciclos de vida (State)

- **Atividade:** Aberta → Atribuída → Revisão → Concluída (Revisão pode voltar para Atribuída); Aberta/Atribuída/Revisão → Cancelada
- **Projeto:** Planejamento → Em andamento → Concluído; Planejamento/Em andamento → Cancelado
- **Denúncia:** Aberta → Em análise → Procedente; Aberta/Em análise → Arquivada

## Como executar

Requisitos: **JDK 21 ou superior** (o Maven baixa o JavaFX automaticamente).

```bash
./mvnw clean javafx:run          # Linux/macOS
mvnw.cmd clean javafx:run        # Windows
```

No IntelliJ: abra a pasta do projeto como projeto Maven e rode `Programa.main`.

### Demonstração dos padrões no console

A classe `DemonstracaoPadroes` executa os quatro padrões sem abrir a interface
(útil na apresentação). No IntelliJ, clique com o botão direito nela → *Run*.

## Dados

Os dados ficam em `projetos.dat`, `atividades.dat`, `denuncias.dat` e `colaboradores.dat`,
criados automaticamente na pasta de execução na primeira gravação.
