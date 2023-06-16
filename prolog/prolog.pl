
% exemplo de movimentos de cartao de credito
movimento_cartao('Conta1', '+', 100.0, 5.0, date(2023, 1, 1), 1).
movimento_cartao('Conta2', '-', 50.0, 2.5, date(2023, 1, 15), 1).
movimento_cartao('Conta3', '+', 200.0, 10.0, date(2023, 2, 1), 2).
movimento_cartao('Conta4', '-', 100.0, 5.0, date(2023, 2, 15), 1).

% funcao que retorna o total de gastos no cartao de credito
valores_extrato_entre_datas(DataInicio, DataFim, Total, TotalFracionado) :-
    
    findall(Valor, (movimento_cartao(_, Tipo, Valor, _, Data, Meses), 
                    Data @>= DataInicio, Data @=< DataFim, 
                    (Tipo = '+' -> true; Tipo = '-' -> Valor is -Valor)), 
            Valores),
            % write(Valores), nl,
    % Encontra os valores fracionados
    findall(ValorFracionado, (member(Valor, Valores), ValorFracionado is Valor / Meses), ValoresFracionados),
    % Implementa o somatorio dos valores
    sumlist(Valores, Total),
    % Implementa o somatorio dos valores fracionados
    sumlist(ValoresFracionados, TotalFracionado).
