(ns aula5.exercicio
  (:require [aula5.data :as data]))


;; Utilizar loop/recur para resolver os exercicios


;; retornar o vetor de clientes a chave `:full-name` adicionada nos mapas de clientes. (full-name deve ser a concatenacao do `:first-name` com o `:last-name`).
(defn full-name
  [data]
    (loop [available data
           result []]
      (if (not-empty available)
        ;(let [{first-name :first-name last-name :last-name :as cliente} (first available)
        (let [cliente (first available)
              first-name (:first-name cliente)
              last-name (:last-name cliente)
              client-with-full-name (assoc cliente :full-name (str first-name " " last-name))
              new-available (rest available)
              new-result (conj result client-with-full-name)]
          (recur new-available new-result))
        result)))

;; retornar um mapa de compras, onde a chave deve ser o id do cliente e o valor deve ser um vetor que contendo todas as compras do cliente.
(defn products-map
  [data]
  (loop [available data
         result {}]
    (if (not-empty available)
      (let [purchase (first available)
            key-item (:client-id purchase)
            purchase-without-client-id (dissoc purchase :client-id)
            acc-value (get result key-item [])
            new-value (conj acc-value purchase-without-client-id)]
        (recur (rest available) (assoc result key-item new-value)))
      result)))

;; retornar o vetor de clientes com a chave `:purchases`. O valor dessa chave sera um vetor contendo mapas com `:item-id` e `:amount`.


;; escrever uma funcao que tras a frequencia de cada caracter em uma string.


;; testes manuais
(comment
  (full-name data/clients)
  (products-map data/purchases)
  (purchases-by-client data/purchases)
  (clients-with-purchases data/clients data/purchases))