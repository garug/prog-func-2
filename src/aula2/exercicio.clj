(ns aula2.exercicio
  (:require [aula2.data :as data]))


;; criar um vetor com nome de clientes que conterá todos os clientes de ambas as colecões do namespace `aula2.data`.
(def clientes (into [] (concat data/clientes-lista data/clientes-vetor)))

;; adicionar um cliente no vetor `clientes`.
(defn adiciona-cliente
  "Adiciona um novo cliente ao vetor de clientes"
  [cliente clientes]
  (conj clientes cliente))

;; retornar o cliente na posicão `p` do vetor clientes.
(defn busca-cliente
  "Busca cliente na posição indicada"
  [indice clientes]
  (get clientes indice))

;; retornar o nome do cliente mais novo do vetor `clientes`.
(defn busca-cliente-mais-recente
  "Busca cliente mais recente do vetor"
  [clientes]
  (:first-name (peek clientes)))

;; remover o cliente mais novo do vetor `clientes`.
(defn remove-cliente-mais-recente
  "Remove cliente mais recente do vetor"
  [clientes]
  (pop clientes))

;; retornar o nome do cliente mais antigo do vetor `clientes`.
(defn busca-cliente-mais-antigo
  "Busca cliente mais antigo do vetor"
  [clientes]
  (:first-name (first clientes)))

;; remover o cliente mais antigo do vetor `clientes`.
(defn remove-cliente-mais-antigo
  "Remove cliente mais antigo do vetor"
  [clientes]
  (rest clientes))

;; remover do vetor `clientes` os clientes com o `last-year-revenue` menor que 50000 (dica: usar `remove` ou `filter`, procurar na documentacão);
(defn remove-clientes-sem-grana
  "Remove clientes com 'last-year-revenue' menor que 50000"
  [clientes]
  ; (filter #(> (:last-year-revenue %) 50000) clientes)); utilizando função anônima
  (filter (fn [cliente ] (> (:last-year-revenue cliente) 50000)) clientes)) ; utilizando fn

(comment
  (adiciona-cliente {:first-name         "Garug"
                     :last-name          "Santana"
                     :age                20
                     :last-year-revenue  2
                     :location           "DF"} clientes)
  (busca-cliente 2 clientes)
  (busca-cliente-mais-recente (adiciona-cliente {:first-name         "Garug"
                                                 :last-name          "Santana"
                                                 :age                20
                                                 :last-year-revenue  2
                                                 :location           "DF"} clientes))
  (busca-cliente-mais-recente clientes)
  (remove-cliente-mais-recente clientes)
  (busca-cliente-mais-antigo clientes)
  (remove-cliente-mais-antigo clientes)
  (remove-clientes-sem-grana clientes))