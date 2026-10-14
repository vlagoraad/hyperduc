    package com.example.projetoversaofinal;

    import java.io.Serializable;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;


    public class Denuncia implements Serializable {

        private static final long serialVersionUID = 1L;
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        private int id;
        private String motivo;
        private String descricao;
        private String alvo;          
        private LocalDate dataCriacao;
        private EstadoDenuncia estado;

        public Denuncia(int id, String motivo, String descricao, String alvo, LocalDate dataCriacao) {
            this.id = id;
            this.motivo = motivo;
            this.descricao = descricao;
            this.alvo = alvo;
            this.dataCriacao = dataCriacao;
            this.estado = new EstadoDenuncia.Aberta(); 
        }

        
        public void analisar()         { estado.analisar(this); }
        public void julgarProcedente() { estado.julgarProcedente(this); }
        public void arquivar()         { estado.arquivar(this); }

        void setEstado(EstadoDenuncia estado) {
            this.estado = estado;
        }

        public EstadoDenuncia getEstado() {
            return estado;
        }

        public String getStatus() {
            return estado.getNome();
        }

    
        public Memento salvarEstado() {
            return new Memento(motivo, descricao, alvo, estado);
        }

        public void restaurarEstado(Memento m) {
            this.motivo = m.motivo;
            this.descricao = m.descricao;
            this.alvo = m.alvo;
            this.estado = m.estado;
        }

        public static final class Memento {
            private final String motivo;
            private final String descricao;
            private final String alvo;
            private final EstadoDenuncia estado;

            private Memento(String motivo, String descricao, String alvo, EstadoDenuncia estado) {
                this.motivo = motivo;
                this.descricao = descricao;
                this.alvo = alvo;
                this.estado = estado;
            }
        }

        
        public int getId() { return id; }
        public String getMotivo() { return motivo; }
        public String getDescricao() { return descricao; }
        public String getAlvo() { return alvo; }
        public LocalDate getDataCriacao() { return dataCriacao; }

        public void setMotivo(String motivo) { this.motivo = motivo; }
        public void setDescricao(String descricao) { this.descricao = descricao; }
        public void setAlvo(String alvo) { this.alvo = alvo; }

        public String getDataCriacaoFormatada() {
            return dataCriacao == null ? "Data não encontrada" : dataCriacao.format(FORMATTER);
        }

        @Override
        public String toString() {
            return "Denuncia{id=" + id + ", motivo='" + motivo + "', alvo='" + alvo + "', estado=" + getStatus() + "}";
        }
    }
